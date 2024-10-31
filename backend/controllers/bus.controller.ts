import { Request, Response } from "express";
import { createBusValidator, deleteBusValidator, getBusByRouteValidator, getLocationValidator, updateBusValidator } from "../validators/bus.validator";
import prisma from "../db";
export async function addBus(req: Request, res: Response) {
    try {
        const body = req.body;
        const check = createBusValidator.safeParse(body);
        if (!check.success) {
            throw new Error(check.error.message);
        }
        const bus = await prisma.bus.findUnique({
            where: {
                driverId: check.data.driverId,
            },
        });
        if (bus) {
            throw new Error("Driver already has a bus");
        }
        await prisma.bus.create({
            data: {
                busno: check.data.busno,
                routeId: check.data.routeId,
                driverId: check.data.driverId,
            },
        });
        res.json({
            success: true,
            message: "Bus added successfully",
        });
        return;
    } catch (error: any) {
        console.log(error)
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }

}
export async function getAll(req: Request, res: Response) {
    try {
        const response = await prisma.bus.findMany({})
        console.log(response)
        res.json({
            success: true,
            message: response
        })
        return
    } catch (error) {
        res.json({
            success: false,
            message: `${error}`
        })
        return
    }
}
export async function getBus(req: Request, res: Response) {
    try {
        const busId = req.query.busId;
        if (!busId) {
            throw new Error("Bus id is required")
        }
        const bus = await prisma.bus.findUnique({
            where: {
                id: busId as string,
            },
        });
        if (!bus) {
            throw new Error("Bus not found");
        }
        res.json({
            success: true,
            data: bus,
        });
        return;
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function updateBus(req: Request, res: Response) {
    try {
        const body = req.body;
        const check = updateBusValidator.safeParse(body);
        if (!check.success) {
            throw new Error(check.error.message);
        }
        await prisma.bus.update({
            where: {
                id: check.data.busId,
            },
            data: {
                busno: check.data.busno,
                routeId: check.data.routeId,
                driverId: check.data.driverId,
            },
        });
        res.json({
            success: true,
            message: "Bus updated successfully",
        });
        return;

    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function deleteBus(req: Request, res: Response) {
    try {
        const body = req.body;
        const check = deleteBusValidator.safeParse(body);
        if (!check.success) {
            throw new Error(check.error.message);
        }
        const bus = await prisma.bus.findUnique({
            where: {
                id: check.data.busId,
            },
        });
        if (!bus) {
            throw new Error("Bus not found");
        }
        await prisma.bus.delete({
            where: {
                id: check.data.busId,
            },
        });
        res.json({
            success: true,
            message: "Bus deleted successfully",
        });
        return;
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function getBusByRoute(req: Request, res: Response) {
    try {
        const body = req.query
        const check = getBusByRouteValidator.safeParse(body);
        if (!check.success) {
            throw new Error(check.error.message);
        }
        const buses = await prisma.bus.findMany({
            where: {
                routeId: check.data.routeId,
            },
        });
        res.json({
            success: true,
            data: buses,
        });
        return;
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}
export async function getLocation(req: Request, res: Response) {
    try {
        const body = req.query;
        const check = getLocationValidator.safeParse(body);
        if (!check.success) {
            throw new Error(check.error.message);
        }
        const bus = await prisma.bus.findUnique({
            where: {
                id: check.data.busId,
            },
        });
        if (!bus) {
            throw new Error("Bus not found");
        }
        res.json({
            success: true,
            data: {
                lat: bus.lat,
                long: bus.long,
            },
        });
        return;
    } catch (error: any) {
        res.json({
            success: false,
            message: error.message || "Internal Server Error"
        })
    }
}