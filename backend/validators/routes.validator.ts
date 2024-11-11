import z from 'zod';
export const addRouteValidator = z.object({
    routeName: z.string(),
})

export const updateRouteValidator = z.object({
    routeId: z.string(),
    routeName: z.string()
})
export const deleteRouteValidator = z.object({
    routeId: z.string(),
})
