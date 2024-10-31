import { Request, Response } from "express";
import { userLoginvalidator } from "../validators/user.validator";
import prisma from "../db";
import bcrypt from "bcryptjs"
import jwt from "jsonwebtoken"
import { AddDriverValidator } from "../validators/driver.validator";
import { AssignDriverValidator, removeDriverValidator } from "../validators/admin.validator";
export async function login(req: Request, res: Response) {
    try {
        const body = req.body
        const check = userLoginvalidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const admin = await prisma.admin.findUnique({
            where: {
                email: check.data.email
            }
        })
        if (!admin) {
            throw new Error("Invalid email or password")
        }
        const valid = bcrypt.compareSync(check.data.password, admin.password)
        if (!valid) {
            throw new Error("Invalid email or password")
        }
        const token = jwt.sign({
            id: admin.id,
            role: "admin"
        }, process.env.JWT_SECRET!)
        res.json({
            success: true,
            token
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function addDriver(req: Request, res: Response) {
    try {
        const body = req.body
        const check = AddDriverValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const salt = bcrypt.genSaltSync(10)
        await prisma.driver.create({
            data: {
                name: check.data.name,
                email: check.data.email,
                password: bcrypt.hashSync(check.data.password, salt)
            }
        })
        res.json({
            success: true,
            data: "Driver added"
        })
        return

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function removeDriver(req: Request, res: Response) {
    try {
        const body = req.body
        const check = removeDriverValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        await prisma.driver.delete({
            where: {
                id: check.data.driverId
            }
        })
        res.json({
            success: true,
            data: "Driver removed"
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function AssignDriver(req: Request, res: Response) {
    try {
        const body = req.body
        const check = AssignDriverValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const response = await prisma.bus.update({
            where: {
                id: check.data.busId
            },
            data: {
                driverId: check.data.driverId
            }
        })
        res.json({
            success: true,
            data: "Driver assigned"
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
