import z from "zod"
export const addStopValidator = z.object({
    routeId: z.string(),
    lat: z.number(),
    long: z.number(),
    description: z.string(),
    time: z.string()
})
export const editStopValidator = z.object({
    stopId: z.string(),
    lat: z.number(),
    long: z.number(),
    description: z.string(),
    time: z.string()
})
export const deleteStopValidator = z.object({
    stopId: z.string()
})
