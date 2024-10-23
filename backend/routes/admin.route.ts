import { Router } from "express";
import { adminMiddleware } from "../middleware/admin.middleware";
import { addDriver, AssignDriver, login, removeDriver } from "../controllers/admin.controller";
export const AdminRouter = Router()
AdminRouter.post("/login", login)
AdminRouter.post("/addDriver", adminMiddleware, addDriver)
AdminRouter.post("/removeDriver", adminMiddleware, removeDriver)
AdminRouter.post("/AssignDriver", adminMiddleware, AssignDriver)