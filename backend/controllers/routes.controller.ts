import { Request, Response } from "express";
export async function addRoute(req: Request, res: Response) {
    res.send("addRoute")
}
export async function getRoute(req: Request, res: Response) {
    res.send("getRoute")
}
export async function updateRoute(req: Request, res: Response) {
    res.send("updateRoute")
}
export async function deleteRoute(req: Request, res: Response) {
    res.send("deleteRoute")
}
export async function getAllRoutes(req: Request, res: Response) {
    res.send("getAllRoutes")
}