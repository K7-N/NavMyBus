"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const user_route_1 = require("./routes/user.route");
const driver_route_1 = require("./routes/driver.route");
const admin_route_1 = require("./routes/admin.route");
const stops_route_1 = require("./routes/stops.route");
const routes_route_1 = require("./routes/routes.route");
const bus_route_1 = require("./routes/bus.route");
const cors_1 = __importDefault(require("cors"));
const app = (0, express_1.default)();
const port = 3000;
app.get("/", (req, res) => {
    res.json({
        health: "success"
    });
});
app.use(express_1.default.json());
app.use((0, cors_1.default)());
app.use("/api/v1/user", user_route_1.userRouter);
app.use("/api/v1/driver", driver_route_1.DriverRouter);
app.use("/api/v1/admin", admin_route_1.AdminRouter);
app.use("/api/v1/stops", stops_route_1.stopsRouter);
app.use("/api/v1/route", routes_route_1.routesRouter);
app.use("/api/v1/bus", bus_route_1.busRouter);
app.listen(port, () => {
    console.log(`server is listening at http://localhost:${port}/`);
});
