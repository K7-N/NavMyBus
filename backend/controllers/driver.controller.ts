import { Request, Response } from "express";
export async function login(req: Request, res: Response) {
    res.send("login")
}
export async function toggleLive(req: Request, res: Response) {
    res.send("toggleLive")
}
export async function updateLocation(req: Request, res: Response) {
    res.send("updateLocation")
}
export async function getDriver(req: Request, res: Response) {
    res.send("getDriver")
}