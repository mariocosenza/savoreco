"use strict";

async function saveImage(imageFile) {
    if (imageFile) {
        const imageFormData = new FormData();
        const maxSizeInBytes = 10 * 1024 * 1024; //10 MB

        if (imageFile.type !== "image/png") {
            alert("Please select a PNG image file.");
            return "error";
        }

        if (imageFile.size > maxSizeInBytes) {
            alert("The file size should not exceed 10 MB.");
            return "error";
        }

        const mode = "restaurant";
        imageFormData.append("mode", mode)
        imageFormData.append("image", imageFile);
        const imageResponse = await fetch("/fileUpload", {
            method: "POST",
            body: imageFormData
        });

        if (!imageResponse.ok) {
            alert(":(");
            throw new Error("Failed to upload image");
        }

        return imageResponse.text();
    }
    return "";
}

