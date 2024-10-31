import z from "zod"
export const DriverLoginValidator = z.object({
    email: z.string().email(),
    password: z.string()
})
export const AddDriverValidator = z.object({
    email: z.string().email(),
    password: z.string(),
    name: z.string(),
})

export const DriverToggleLiveValidator = z.object({
    live: z.boolean()
})

export const UpdateLocationValidator = z.object({
    long: z.number(),
    lat: z.number()
})

export const GetDriverValidator = z.object({
    DriverId: z.string()
})