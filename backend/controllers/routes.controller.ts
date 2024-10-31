import { Request, Response } from "express";
import { addRouteValidator, deleteRouteValidator, updateRouteValidator } from "../validators/routes.validator";
import prisma from "../db";
export async function addRoute(req: Request, res: Response) {
    try {
        const body = req.body
        const check = addRouteValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        await prisma.routes.create({
            data: {
                routeName: check.data.routeName,
                startlat: check.data.startlat,
                startlong: check.data.startlong,
                endlat: check.data.endlat,
                endlong: check.data.endlong,
            }
        })
        res.json({
            success: true,
            message: "Route added successfully"
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function getRoute(req: Request, res: Response) {
    try {
        const routeId = req.query.routeId as string
        if (!routeId) {
            throw new Error("RouteId is required")
        }
        const route = await prisma.routes.findUnique({
            where: {
                id: routeId
            },
            include: {
                stops: true
            }
        })
        if (!route) {
            throw new Error("Route not found")
        }
        res.json({
            success: true,
            data: route
        })
        return

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function updateRoute(req: Request, res: Response) {
    try {
        const body = req.body
        const check = updateRouteValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const route = await prisma.routes.findUnique({
            where: {
                id: check.data.routeId
            }
        })
        if (!route) {
            throw new Error("Route not found")
        }
        await prisma.routes.update({
            where: {
                id: check.data.routeId
            },
            data: {
                routeName: check.data.routeName,
                startlat: check.data.startlat,
                startlong: check.data.startlong,
                endlat: check.data.endlat,
                endlong: check.data.endlong,
            }
        })
        res.json({
            success: true,
            message: "Route updated"
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
export async function deleteRoute(req: Request, res: Response) {
    try {
        const body = req.body
        const check = deleteRouteValidator.safeParse(body)
        if (!check.success) {
            throw new Error(check.error.message)
        }
        const route = await prisma.routes.findUnique({
            where: {
                id: check.data.routeId
            }
        })
        if (!route) {
            throw new Error("Route not found")
        }
        await prisma.routes.delete({
            where: {
                id: check.data.routeId
            }
        })
        res.json({
            success: true,
            message: "Route deleted successfully"
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function getAllRoutes(req: Request, res: Response) {
    try {
        const routes = await prisma.routes.findMany()
        res.json({
            success: true,
            data: routes
        })
        return
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}