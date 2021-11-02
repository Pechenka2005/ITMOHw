'use strict';

/**
 * Складывает два целых числа
 * @param {Number} a Первое целое
 * @param {Number} b Второе целое
 * @throws {TypeError} Когда в аргументы переданы не числа
 * @returns {Number} Сумма аргументов
 */
function abProblem(a, b) {
    if (typeof a !== 'number' || typeof b !== 'number') {
        throw new TypeError('a or b is not number');
    }
    return a + b;
}

/**
 * Определяет век по году
 * @param {Number} year Год, целое положительное число
 * @throws {TypeError} Когда в качестве года передано не число
 * @throws {RangeError} Когда год – отрицательное значение
 * @returns {Number} Век, полученный из года
 */
function centuryByYearProblem(year) {
    if (typeof year !== 'number') {
        throw new TypeError('Year is not number');
    }
    if (year < 0) {
        throw new RangeError('Year < 0');
    }
    return Math.floor(year / 100) + 1;


}

/**
 * Переводит цвет из формата HEX в формат RGB
 * @param {String} hexColor Цвет в формате HEX, например, '#FFFFFF'
 * @throws {TypeError} Когда цвет передан не строкой
 * @throws {RangeError} Когда значения цвета выходят за пределы допустимых
 * @returns {String} Цвет в формате RGB, например, '(255, 255, 255)'
 */
function colorsProblem(hexColor) {
    if (typeof hexColor !== 'string') {
        throw new TypeError('hexColor is not string');
    }
    if (hexColor.substring(0, 1) === '#') {
        hexColor = hexColor.substring(1);
    } else {
        throw new TypeError('wrong hex');
    }

    function checkRange(a) {
        if (a >= 0 && a <= 255) {
            return true;
        }
        return false;
    }
    let r = +hexColor.substring(0, 2);
    let g = +hexColor.substring(2, 4);
    let b = +hexColor.substring(4, 6);
    if (!r || !b || !g || !checkRange(a) || !checkRange(b) || !checkRange(b)) {
        throw new RangeError('Invalid hex values');
    }
    let arr = [r, g, b];
    return '(' + arr.join(', ') + ')';
}

/**
 * Находит n-ое число Фибоначчи
 * @param {Number} n Положение числа в ряде Фибоначчи
 * @throws {TypeError} Когда в качестве положения в ряде передано не число
 * @throws {RangeError} Когда положение в ряде не является целым положительным числом
 * @returns {Number} Число Фибоначчи, находящееся на n-ой позиции
 */
function fibonacciProblem(n) {
    if (typeof n !== 'number') {
        throw new TypeError('n is not number');
    }
    if (n - Math.floor(n) > 0) {
        throw new RangeError('n is not integer');
    }
    let fib = [1, 1];
    for (let i = 2; i <= n + 1; i++) {
        fib.push(fib[i - 1] + fib[i - 2]);
    }
    return

}

/**
 * Транспонирует матрицу
 * @param {(Any[])[]} matrix Матрица размерности MxN
 * @throws {TypeError} Когда в функцию передаётся не двумерный массив
 * @returns {(Any[])[]} Транспонированная матрица размера NxM
 */
function matrixProblem(matrix) {
    if (!Array.isArray(matrix) || matrix.length === 0 || !Array.isArray(matrix[0])) {
        throw new TypeError('matrix is not double array');
    }
    let result = [];
    for (let i = 0; i < matrix[0].length; i++) {
        result.push([]);
    }
    for (let i = 0; i < matrix.length; i++) {
        for (let j = 0; j < matrix[0].length; j++) {
            result[j][i] = matrix[i][j];
        }
    }
    return result;
}

/**
 * Переводит число в другую систему счисления
 * @param {Number} n Число для перевода в другую систему счисления
 * @param {Number} targetNs Система счисления, в которую нужно перевести (Число от 2 до 36)
 * @throws {TypeError} Когда переданы аргументы некорректного типа
 * @throws {RangeError} Когда система счисления выходит за пределы значений [2, 36]
 * @returns {String} Число n в системе счисления targetNs
 */
function numberSystemProblem(n, targetNs) {
    if (typeof n !== "number" || typeof targetNs !== "number" || n - Math.floor(n) > 0 || targetNs - Math.floor(targetNs) > 0) {
        throw new TypeError('uncorrect input');
    }
    if (targetNs < 2 || targetNs > 36) {
        throw new RangeError('uncorrect input');
    }
    let arr = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];

    function get(x) {
        if (x < 10) {
            return x;
        }
        return arr[x - 10];
    }
    let result = [];
    while (n > 0) {
        if (n < targetNs) {
            result.push(get(n));
            n = 0;
        } else {
            result.push(get(n % targetNs));
            n = math.floor(n / targetNs);
        }
    }
    return result.reverse.join('');
}

/**
 * Проверяет соответствие телефонного номера формату
 * @param {String} phoneNumber Номер телефона в формате '8–800–xxx–xx–xx'
 * @throws {TypeError} Когда в качестве аргумента передаётся не строка
 * @returns {Boolean} Если соответствует формату, то true, а иначе false
 */
function phoneProblem(phoneNumber) {
    if (typeof phoneNumber !== "string") {
        throw new TypeError('uncorrect input');
    }
    const regExp = /^8-800-(\d){3}-(\d){2}-(\d){2}$/;
    return regExp.test(phoneNumber);
}

/**
 * Определяет количество улыбающихся смайликов в строке
 * @param {String} text Строка в которой производится поиск
 * @throws {TypeError} Когда в качестве аргумента передаётся не строка
 * @returns {Number} Количество улыбающихся смайликов в строке
 */
function smilesProblem(text) {
    if (typeof phoneNumber !== "string") {
        throw new TypeError('uncorrect input');
    }
    let matchAll = text.matchAll(/:-)/);
    matchAll = Array.from(matchAll);
    return matchAll.length;
}

/**
 * Определяет победителя в игре "Крестики-нолики"
 * Тестами гарантируются корректные аргументы.
 * @param {(('x' | 'o')[])[]} field Игровое поле 3x3 завершённой игры
 * @returns {'x' | 'o' | 'draw'} Результат игры
 */
function ticTacToeProblem(field) {
    function isWin(char) {
        if (test(char, 0, 0, 1, 0) ||
            test(char, 0, 0, 1, 1) ||
            test(char, 0, 0, 0, 1) ||
            test(char, 1, 0, 0, 1) ||
            test(char, 2, 0, 0, 1) ||
            test(char, 2, 0, -1, 1) ||
            test(char, 0, 1, 1, 0) ||
            test(char, 0, 2, 1, 0)) {
            return true;
        }
        return false;
    }

    function test(char, x, y, xDiff, yDiff) {
        for (let i = 0; i < 3; i++) {
            if (field[x][y] !== char) {
                return false;
            }
            x += xDiff;
            y += yDiff;
        }
        return true;
    }
    if (isWin('x')) {
        return 'x';
    } else if (isWin('o')) {
        return 'o';
    } else {
        return 'draw';
    }
}

module.exports = {
    abProblem,
    centuryByYearProblem,
    colorsProblem,
    fibonacciProblem,
    matrixProblem,
    numberSystemProblem,
    phoneProblem,
    smilesProblem,
    ticTacToeProblem
};