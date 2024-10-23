import { Request, Response } from "express";
export async function addBus(req: Request, res: Response) {
    res.send("addBus")
}
export async function getBus(req: Request, res: Response) {
    res.send("getBus")
}
export async function updateBus(req: Request, res: Response) {
    res.send("updateBus")
}
export async function deleteBus(req: Request, res: Response) {
    res.send("deleteBus")
}
export async function getBusByRoute(req: Request, res: Response) {
    res.send("getBusByRoute")
}
export async function getLocation(req: Request, res: Response) {
    res.send("getLocation")
}