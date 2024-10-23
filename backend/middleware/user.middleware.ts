import { Request, Response, NextFunction } from "express";
export async function userMiddleware(req: Request, res: Response, next: NextFunction) {
    console.log("user middleware");
    next();
}
