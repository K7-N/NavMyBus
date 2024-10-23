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
export async function addDriver(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function removeDriver(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function AssignDriver(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
