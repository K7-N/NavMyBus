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
export async function toggleLive(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function updateLocation(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function getDriver(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}