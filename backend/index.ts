import express from "express"

const app = express()
const port = 3000;

app.get("/", (req, res) => {
    res.json({
        health: "success"
    })
})
app.listen(port, () => {
    console.log(`server is listening at http://localhost:${port}/`)
})