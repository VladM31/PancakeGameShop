export function isPastDate(date) {
    const inputDate = new Date(date ? date : new Date());
    if (isNaN(inputDate)) {
        throw new Error("Invalid date format");
    }

    const currentDate = new Date();
    currentDate.setHours(0, 0, 0, 0);

    return inputDate <= currentDate;
}