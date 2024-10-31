import z from 'zod';
export const addRouteValidator = z.object({
    routeName: z.string(),
    startlat: z.number(),
    startlong: z.number(),
    endlat: z.number(),
    endlong: z.number(),
})

export const updateRouteValidator = z.object({
    routeId: z.string(),
    routeName: z.string(),
    startlat: z.number(),
    startlong: z.number(),
    endlat: z.number(),
    endlong: z.number(),
})
export const deleteRouteValidator = z.object({
    routeId: z.string(),
})
