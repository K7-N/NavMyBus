import { Request, Response, NextFunction } from "express";
import jwt from "jsonwebtoken"
export async function driverMiddleware(req: Request, res: Response, next: NextFunction) {
    try {
        const token = req.headers.authorization
        if (!token) {
            throw new Error("Unauthorized")
        }
        const user: any = jwt.verify(token, process.env.JWT_SECRET!)
        if (!user) {
            throw new Error("Unauthorized")
        }
        if (user.role == "user") {
            throw new Error("Unauthorized")
        }
        req.body.driverId = user.id
        next()
    } catch (error) {
        res.json({
            success: false,
            message: "unauthorized"
        })
    }
}