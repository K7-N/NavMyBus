import { Router } from "express";
import { getDriver, login, toggleLive, updateLocation } from "../controllers/driver.controller";
import { driverMiddleware } from "../middleware/driver.middleware";
export const DriverRouter = Router()
DriverRouter.post("/login", login)
DriverRouter.post("/toggleLive", driverMiddleware, toggleLive)
DriverRouter.post("/updateLocation", driverMiddleware, updateLocation)
DriverRouter.get("/getDriver", driverMiddleware, getDriver)