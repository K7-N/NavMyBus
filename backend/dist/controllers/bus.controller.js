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
exports.addBus = addBus;
exports.getBus = getBus;
exports.updateBus = updateBus;
exports.deleteBus = deleteBus;
exports.getBusByRoute = getBusByRoute;
exports.getLocation = getLocation;
function addBus(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("addBus");
    });
}
function getBus(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("getBus");
    });
}
function updateBus(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("updateBus");
    });
}
function deleteBus(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("deleteBus");
    });
}
function getBusByRoute(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("getBusByRoute");
    });
}
function getLocation(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("getLocation");
    });
}
