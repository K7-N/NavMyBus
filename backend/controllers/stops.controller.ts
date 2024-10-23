import { Request, Response } from "express";
export async function addStop(req: Request, res: Response) {
    res.send("addStop")
}

export async function editStop(req: Request, res: Response) {
    res.send("updateStop")
}

export async function deleteStop(req: Request, res: Response) {
    res.send("deleteStop")
}