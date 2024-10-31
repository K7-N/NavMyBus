import z from 'zod';
export const removeDriverValidator = z.object({
    driverId: z.string()
});
export const AssignDriverValidator = z.object({
    driverId: z.string(),
    busId: z.string()
})