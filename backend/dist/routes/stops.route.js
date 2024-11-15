"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.stopsRouter = void 0;
const express_1 = require("express");
const admin_middleware_1 = require("../middleware/admin.middleware");
const stops_controller_1 = require("../controllers/stops.controller");
exports.stopsRouter = (0, express_1.Router)();
exports.stopsRouter.post("/addStop", admin_middleware_1.adminMiddleware, stops_controller_1.addStop);
exports.stopsRouter.delete("/deleteStop", admin_middleware_1.adminMiddleware, stops_controller_1.deleteStop);
exports.stopsRouter.put("/editStop", admin_middleware_1.adminMiddleware, stops_controller_1.editStop);
