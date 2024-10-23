import { Request, Response, NextFunction } from "express";
import jwt from "jsonwebtoken";
export async function userMiddleware(req: Request, res: Response, next: NextFunction) {
    try {
        const token = req.headers.authorization
        if (!token) {
            throw new Error("Unauthorized")
        }
        const user = jwt.verify(token, process.env.JWT_SECRET!)
        if (!user) {
            throw new Error("Unauthorized")
        }

        req.body.id = (user as any).id
        next()
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }

}
