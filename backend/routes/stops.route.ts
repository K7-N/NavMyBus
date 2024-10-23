import { Router } from "express";
import { adminMiddleware } from "../middleware/admin.middleware";
import { addStop, deleteStop, editStop } from "../controllers/stops.controller";
export const stopsRouter = Router();
stopsRouter.post("/addStop", adminMiddleware, addStop);
stopsRouter.delete("/deleteStop", adminMiddleware, deleteStop);
stopsRouter.put("/editStop", adminMiddleware, editStop);