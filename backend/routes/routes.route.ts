import { Router } from "express";
import { adminMiddleware } from "../middleware/admin.middleware";
import { addRoute, deleteRoute, getAllRoutes, getRoute, updateRoute } from "../controllers/routes.controller";
import { userMiddleware } from "../middleware/user.middleware";
export const routesRouter = Router()
routesRouter.post("/addRoute", adminMiddleware, addRoute)
routesRouter.get("/getRoute", userMiddleware, getRoute)
routesRouter.put("/updateRoute", adminMiddleware, updateRoute)
routesRouter.delete("/deleteRoute", adminMiddleware, deleteRoute)
routesRouter.get("/getAllRoutes", userMiddleware, getAllRoutes)