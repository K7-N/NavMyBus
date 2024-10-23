import { Request, Response, NextFunction } from "express";
export async function driverMiddleware(req: Request, res: Response, next: NextFunction) {
    console.log("driver middleware");
    next();
}