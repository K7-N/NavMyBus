import { Router } from "express";
import { adminMiddleware } from "../middleware/admin.middleware";
import { addDriver, AssignDriver, login, removeDriver } from "../controllers/admin.controller";
import prisma from "../db";
import bcrypt from "bcryptjs"
import jwt from "jsonwebtoken"
export const AdminRouter = Router()
AdminRouter.post("/login", login)
AdminRouter.post("/signup", async (req, res) => {
    try {
        const salt = bcrypt.genSaltSync(10)
        const hash = bcrypt.hashSync("admin123", salt)
        const response = await prisma.admin.create({
            data: {
                email: "admin@gmail.com",
                password: hash,
            }
        })
        const token = jwt.sign({
            id: response.id,
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
        return
    }
})
AdminRouter.post("/addDriver", adminMiddleware, addDriver)
AdminRouter.post("/removeDriver", adminMiddleware, removeDriver)
AdminRouter.post("/AssignDriver", adminMiddleware, AssignDriver)