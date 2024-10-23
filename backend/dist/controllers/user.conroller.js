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
exports.login = login;
exports.signup = signup;
exports.updateProfile = updateProfile;
exports.getProfile = getProfile;
function login(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("login");
    });
}
function signup(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("signup");
    });
}
function updateProfile(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("updateProfile");
    });
}
function getProfile(req, res) {
    return __awaiter(this, void 0, void 0, function* () {
        res.send("getProfile");
    });
}
