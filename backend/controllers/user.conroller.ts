import { Request, Response } from "express";
export async function login(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function signup(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function updateProfile(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function getProfile(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}