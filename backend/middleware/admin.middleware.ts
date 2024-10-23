import { Request, Response, NextFunction } from "express";
export async function adminMiddleware(req: Request, res: Response, next: NextFunction) {
    console.log("driver middleware");
    next();
}