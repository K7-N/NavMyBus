import { Request, Response } from "express";
export async function addStop(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}

export async function editStop(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}

export async function deleteStop(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}