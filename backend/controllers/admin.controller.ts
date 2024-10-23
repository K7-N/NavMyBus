import { Request, Response } from "express";
export async function login(req: Request, res: Response) {
    res.send("login")
}
export async function addDriver(req: Request, res: Response) {
    res.send("addDriver")
}
export async function removeDriver(req: Request, res: Response) {
    res.send("removeDriver")
}
export async function AssignDriver(req: Request, res: Response) {
    res.send("AssignDriver")
}
