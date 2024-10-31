import z from 'zod';
export const createBusValidator = z.object({
    busno: z.string(),
    routeId: z.string(),
    driverId: z.string()
})
export const updateBusValidator = z.object({
    busId: z.string(),
    busno: z.string(),
    routeId: z.string(),
    driverId: z.string()
})
export const getBusByRouteValidator = z.object({
    routeId: z.string()
})
export const getLocationValidator = z.object({
    busId: z.string()
})
export const getBudValidator = z.object({
    busId: z.string()
})
export const deleteBusValidator = z.object({
    busId: z.string()
})