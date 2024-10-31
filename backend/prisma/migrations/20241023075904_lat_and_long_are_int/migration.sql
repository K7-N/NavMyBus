/*
  Warnings:

  - Changed the type of `startlat` on the `routes` table. No cast exists, the column would be dropped and recreated, which cannot be done if there is data, since the column is required.
  - Changed the type of `startlong` on the `routes` table. No cast exists, the column would be dropped and recreated, which cannot be done if there is data, since the column is required.
  - Changed the type of `endlat` on the `routes` table. No cast exists, the column would be dropped and recreated, which cannot be done if there is data, since the column is required.
  - Changed the type of `endlong` on the `routes` table. No cast exists, the column would be dropped and recreated, which cannot be done if there is data, since the column is required.
  - Changed the type of `lat` on the `stops` table. No cast exists, the column would be dropped and recreated, which cannot be done if there is data, since the column is required.
  - Changed the type of `long` on the `stops` table. No cast exists, the column would be dropped and recreated, which cannot be done if there is data, since the column is required.

*/
-- AlterTable
ALTER TABLE "routes" DROP COLUMN "startlat",
ADD COLUMN     "startlat" INTEGER NOT NULL,
DROP COLUMN "startlong",
ADD COLUMN     "startlong" INTEGER NOT NULL,
DROP COLUMN "endlat",
ADD COLUMN     "endlat" INTEGER NOT NULL,
DROP COLUMN "endlong",
ADD COLUMN     "endlong" INTEGER NOT NULL;

-- AlterTable
ALTER TABLE "stops" DROP COLUMN "lat",
ADD COLUMN     "lat" INTEGER NOT NULL,
DROP COLUMN "long",
ADD COLUMN     "long" INTEGER NOT NULL;
