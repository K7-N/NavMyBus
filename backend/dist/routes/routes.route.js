"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.routesRouter = void 0;
const express_1 = require("express");
const admin_middleware_1 = require("../middleware/admin.middleware");
const routes_controller_1 = require("../controllers/routes.controller");
const user_middleware_1 = require("../middleware/user.middleware");
exports.routesRouter = (0, express_1.Router)();
exports.routesRouter.post("/addRoute", admin_middleware_1.adminMiddleware, routes_controller_1.addRoute);
exports.routesRouter.get("/getRoute", user_middleware_1.userMiddleware, routes_controller_1.getRoute);
exports.routesRouter.put("/updateRoute", admin_middleware_1.adminMiddleware, routes_controller_1.updateRoute);
exports.routesRouter.delete("/deleteRoute", admin_middleware_1.adminMiddleware, routes_controller_1.deleteRoute);
exports.routesRouter.get("/getAllRoutes", user_middleware_1.userMiddleware, routes_controller_1.getAllRoutes);
