"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.busRouter = void 0;
const express_1 = require("express");
const admin_middleware_1 = require("../middleware/admin.middleware");
const bus_controller_1 = require("../controllers/bus.controller");
const user_middleware_1 = require("../middleware/user.middleware");
exports.busRouter = (0, express_1.Router)();
exports.busRouter.post("/addBus", admin_middleware_1.adminMiddleware, bus_controller_1.addBus);
exports.busRouter.get("/getBus", user_middleware_1.userMiddleware, bus_controller_1.getBus);
exports.busRouter.put("/updateBus", admin_middleware_1.adminMiddleware, bus_controller_1.updateBus);
exports.busRouter.delete("/deleteBus", admin_middleware_1.adminMiddleware, bus_controller_1.deleteBus);
exports.busRouter.get("/getBusByRoute", user_middleware_1.userMiddleware, bus_controller_1.getBusByRoute);
exports.busRouter.get("/getLocation", user_middleware_1.userMiddleware, bus_controller_1.getLocation);
