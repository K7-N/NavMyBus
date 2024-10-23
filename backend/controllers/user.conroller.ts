import { Request, Response } from "express";
import { userLoginvalidator, userSignupValidator, userUpdateValidator } from "../validators/user.validator";
import prisma from "../db";
import jwt from "jsonwebtoken";
import bcrypt from "bcryptjs";
export async function login(req: Request, res: Response) {
    try {
        const body = req.body
        const check = userLoginvalidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const user = await prisma.user.findUnique({
            where: {
                email: check.data.email
            }
        })
        if (!user) {
            throw new Error("User not found")
        }
        if (!bcrypt.compareSync(check.data.password, user.password)) {
            throw new Error("Invalid Password")
        }
        const token = jwt.sign({
            id: user.id,
            role: "user"
        }, process.env.JWT_SECRET!, {
            expiresIn: "30d"
        })
        res.json({
            success: true,
            message: "Login Successful",
            data: {
                token
            }
        })
        return

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function signup(req: Request, res: Response) {
    try {
        const body = req.body
        const check = userSignupValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const user = await prisma.user.findUnique({
            where: {
                email: check.data.email
            }
        })
        if (user) {
            throw new Error("User already exists")
        }
        const salt = bcrypt.genSaltSync(10)
        const hash = bcrypt.hashSync(check.data.password, salt)
        const newUser = await prisma.user.create({
            data: {
                username: check.data.username,
                email: check.data.email,
                password: hash
            }
        })
        const token = jwt.sign({
            id: newUser.id,
            role: "user"
        }, process.env.JWT_SECRET!, {
            expiresIn: "30d"
        })
        res.json({
            success: true,
            message: "Signup Successful",
            data: {
                token
            }
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
        return
    }
}
export async function updateProfile(req: Request, res: Response) {
    try {
        const body = req.body
        const check = userUpdateValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        await prisma.user.update({
            where: {
                id: req.body.id
            },
            data: {
                username: check.data.username,
                email: check.data.email
            }
        })
        res.json({
            success: true,
            message: "Profile Updated"
        })
        return

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
        return
    }
}
export async function getProfile(req: Request, res: Response) {
    try {
        const user = await prisma.user.findUnique({
            where: {
                id: req.body.id
            },
            select: {
                id: true,
                username: true,
                email: true
            }
        })
        if (!user) {
            throw new Error("User not found")
        }
        res.json({
            success: true,
            message: "User Profile",
            data: user
        })
        return

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}