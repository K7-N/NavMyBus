import express from "express"
import { userRouter } from "./routes/user.route";
import { DriverRouter } from "./routes/driver.route";
import { AdminRouter } from "./routes/admin.route";
import { stopsRouter } from "./routes/stops.route";
import { routesRouter } from "./routes/routes.route";
import { busRouter } from "./routes/bus.route";
import cors from "cors"
const app = express()
const port = 3000;

app.get("/", (req, res) => {
    res.json({
        health: "success"
    })
})
app.use(express.json())
app.use(cors())
app.use("/api/v1/user", userRouter)
app.use("/api/v1/driver", DriverRouter)
app.use("/api/v1/admin", AdminRouter)
app.use("/api/v1/stops", stopsRouter)
app.use("/api/v1/route", routesRouter)
app.use("/api/v1/bus", busRouter)
app.listen(port, () => {
    console.log(`server is listening at http://localhost:${port}/`)
})