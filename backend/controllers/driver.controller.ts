import { Request, Response } from "express";
import { DriverLoginValidator, DriverToggleLiveValidator, GetDriverValidator, UpdateLocationValidator } from "../validators/driver.validator";
import prisma from "../db";
import bcrypt from "bcryptjs"
import jwt from "jsonwebtoken"
export async function login(req: Request, res: Response) {
    try {
        const body = req.body
        const check = DriverLoginValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const driver = await prisma.driver.findUnique({
            where: {
                email: check.data.email
            }
        })
        if (!driver) {
            throw new Error("Driver not found")
        }
        const correct = await bcrypt.compare(check.data.password, driver.password)
        if (!correct) {
            throw new Error("Incorrect password")
        }
        const token = jwt.sign({
            id: driver.id,
            role: "driver"
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
}
export async function toggleLive(req: Request, res: Response) {
    try {
        const body = req.body
        const check = DriverToggleLiveValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const driver = await prisma.driver.findUnique({
            where: {
                id: req.body.driverId
            }
            , include: {
                bus: true
            }
        })
        if (!driver) {
            throw new Error("Driver not found")
        }
        if (!driver.bus) {
            throw new Error("Bus not found")
        }
        await prisma.bus.update({
            where: {
                id: driver.bus.id
            },
            data: {
                live: check.data.live
            }
        })
        res.json({
            success: true,
            data: "Updated"
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error || "Internal Server Error"
        })
    }
}
export async function updateLocation(req: Request, res: Response) {
    try {
        const body = req.body
        const check = UpdateLocationValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const driver = await prisma.driver.findUnique({
            where: {
                id: req.body.driverId
            },
            include: {
                bus: true
            }
        })
        if (!driver) {
            throw new Error("Driver not found")
        }
        if (!driver.bus) {
            throw new Error("Bus not found")
        }
        const bus = await prisma.bus.findUnique({
            where: {
                id: driver.bus.id
            },

        })
        if (!bus) {
            throw new Error("Bus not found")
        }
        await prisma.bus.update({
            where: {
                id: driver.bus.id
            },
            data: {
                lat: check.data.lat,
                long: check.data.long
            }
        })
        res.json({
            success: true,
            data: "Updated"
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function getAll(req: Request, res: Response) {
    try {
        const drivers = await prisma.driver.findMany({})
        res.json({
            success: true,
            data: drivers
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function getDriver(req: Request, res: Response) {
    try {
        const id = req.query.driverId

        if (!id) {
            throw new Error()
        }
        const driver = await prisma.driver.findUnique({
            where: {
                id: id as string
            }
        })
        if (!driver) {
            throw new Error("Driver not found")
        }
        res.json({
            success: true,
            data: driver
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}