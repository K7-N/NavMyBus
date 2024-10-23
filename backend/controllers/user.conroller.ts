import { Request, Response } from "express";
export async function login(req: Request, res: Response) {
    res.send("login")
}
export async function signup(req: Request, res: Response) {
    res.send("signup")
}
export async function updateProfile(req: Request, res: Response) {
    res.send("updateProfile")
}
export async function getProfile(req: Request, res: Response) {
    res.send("getProfile")
}