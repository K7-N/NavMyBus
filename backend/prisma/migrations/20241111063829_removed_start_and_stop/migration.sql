/*
  Warnings:

  - You are about to drop the column `endlat` on the `routes` table. All the data in the column will be lost.
  - You are about to drop the column `endlong` on the `routes` table. All the data in the column will be lost.
  - You are about to drop the column `startlat` on the `routes` table. All the data in the column will be lost.
  - You are about to drop the column `startlong` on the `routes` table. All the data in the column will be lost.

*/
-- AlterTable
ALTER TABLE "routes" DROP COLUMN "endlat",
DROP COLUMN "endlong",
DROP COLUMN "startlat",
DROP COLUMN "startlong";
