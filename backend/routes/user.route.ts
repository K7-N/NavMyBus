import { Router } from "express";
import { getProfile, login, signup, updateProfile } from "../controllers/user.conroller";
import { userMiddleware } from "../middleware/user.middleware";
export const userRouter = Router()
userRouter.post("/login", login)
userRouter.post("/signup", signup)
userRouter.put("/updateprofile", userMiddleware, updateProfile)
userRouter.get("/getprofile", userMiddleware, getProfile)