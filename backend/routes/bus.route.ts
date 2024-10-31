import { Router } from "express";
import { adminMiddleware } from "../middleware/admin.middleware";
import { addBus, deleteBus, getAll, getBus, getBusByRoute, getLocation, updateBus } from "../controllers/bus.controller";
import { userMiddleware } from "../middleware/user.middleware";
export const busRouter = Router()
busRouter.post("/addBus", adminMiddleware, addBus)
busRouter.get("/getBus", userMiddleware, getBus)
busRouter.get("/getAll", userMiddleware, getAll)
busRouter.put("/updateBus", adminMiddleware, updateBus)
busRouter.delete("/deleteBus", adminMiddleware, deleteBus)
busRouter.get("/getBusByRoute", userMiddleware, getBusByRoute)
busRouter.get("/getLocation", userMiddleware, getLocation)