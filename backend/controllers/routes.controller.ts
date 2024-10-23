import { Request, Response } from "express";
export async function addRoute(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function getRoute(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function updateRoute(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function deleteRoute(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function getAllRoutes(req: Request, res: Response) {
    try {

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}