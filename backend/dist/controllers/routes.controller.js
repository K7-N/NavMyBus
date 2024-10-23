"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.addRoute = addRoute;
exports.getRoute = getRoute;
exports.updateRoute = updateRoute;
exports.deleteRoute = deleteRoute;
exports.getAllRoutes = getAllRoutes;
function addRoute(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("addRoute");
    });
}
function getRoute(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("getRoute");
    });
}
function updateRoute(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("updateRoute");
    });
}
function deleteRoute(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("deleteRoute");
    });
}
function getAllRoutes(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("getAllRoutes");
    });
}
