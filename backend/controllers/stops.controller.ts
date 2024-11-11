import { Request, Response } from "express";
import { addStopValidator, deleteStopValidator, editStopValidator } from "../validators/stops.validator";
import prisma from "../db";
export async function addStop(req: Request, res: Response) {
    try {
        const body = req.body
        const check = addStopValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const stop = await prisma.stops.create({
            data: {
                lat: check.data.lat,
                long: check.data.long,
                description: check.data.description,
                time: check.data.time
            }
        })
        await prisma.routes.update({
            where: {
                id: check.data.routeId
            },
            data: {
                stops: {
                    connect: {
                        id: stop.id
                    }
                }
            }
        })
        res.json({
            success: true,
            message: "Stop added"
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

export async function editStop(req: Request, res: Response) {
    try {
        const body = req.body
        const check = editStopValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const stop = await prisma.stops.findUnique({
            where: {
                id: check.data.stopId
            }
        })
        if (!stop) {
            throw new Error("stop not found")
        }
        await prisma.stops.update({
            where: {
                id: check.data.stopId
            },
            data: {
                lat: check.data.lat,
                long: check.data.long,
                description: check.data.description,
                time: check.data.time
            }
        })
        res.json({
            success: true,
            message: "stop updated"
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}

export async function deleteStop(req: Request, res: Response) {
    try {
        const body = req.body
        const check = deleteStopValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const stop = prisma.stops.findUnique({
            where: {
                id: check.data.stopId
            }
        })
        if (!stop) {
            throw new Error("Stop not found")
        }
        await prisma.stops.delete({
            where: {
                id: check.data.stopId
            }
        })
        res.json({
            success: true,
            message: "stop deleted"
        })
        return

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}