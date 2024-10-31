import { Router } from "express";
import { getAll, getDriver, login, toggleLive, updateLocation } from "../controllers/driver.controller";
import { driverMiddleware } from "../middleware/driver.middleware";
import { userMiddleware } from "../middleware/user.middleware";
export const DriverRouter = Router()
DriverRouter.post("/login", login)
DriverRouter.post("/toggleLive", driverMiddleware, toggleLive)
DriverRouter.post("/updateLocation", driverMiddleware, updateLocation)
DriverRouter.get("/getDriver", userMiddleware, getDriver)
DriverRouter.get("/all", userMiddleware, getAll)