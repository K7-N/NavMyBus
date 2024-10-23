import z from 'zod';
export const userLoginvalidator = z.object({
    email: z.string().email(),
    password: z.string().min(6),
})
export const userSignupValidator = z.object({
    email: z.string().email(),
    password: z.string().min(6),
    username: z.string().min(2),
})
export const userUpdateValidator = z.object({
    email: z.string().email(),
    username: z.string().min(2),
})
