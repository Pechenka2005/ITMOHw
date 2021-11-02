'use strict';

/**
 * Телефонная книга
 */
const phoneBook = new Map();
const regexCreateContact = /^Создай контакт (.+)/;
const regexDeleteContact = /^Удали контакт (.+)$/;
const regexAddForContact = /^Добавь (.+) для контакта (.+)$/;
const regexDelForContact = /^Удали (.+) для контакта (.+)$/;
const regexShowForParameters = /^Покажи (.+) для контактов, где есть (.+)/;
const regexDelForParameters = /^Удали контакты, где есть (.+)/;

/**
 * Вызывайте эту функцию, если есть синтаксическая ошибка в запросе
 * @param {number} lineNumber – номер строки с ошибкой
 * @param {number} charNumber – номер символа, с которого запрос стал ошибочным
 */
function syntaxError(lineNumber, charNumber) {
    throw new Error(`SyntaxError: Unexpected token at ${lineNumber}:${charNumber}`);
}

let answer = [];
/**
 * Выполнение запроса на языке pbQL
 * @param {string} query
 * @returns {string[]} - строки с результатами запроса
 */
function run(query) {
    let row = 1;
    let commands = query.split(";");
    for (let i = 0; i < commands.length; i++) {
        if (commands[i] === "") {
            continue;
        }
        handlerCommand(commands[i], row++);
    }
    const temp = commands[commands.length - 1];
    if (temp !== "") {
        syntaxError(commands.length, temp.length + 1);
    }
    return answer;
}

function checkSpacesAndNumbers(row, command, numbers) {
    let arr = command.split(' ');
    let count = 1;
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] === "") {
            syntaxError(row, count);
        } else if (numbers.includes(arr[i]) && !(/^(\d){10}$/.test(arr[i]))) {
            syntaxError(row, count);
        } else {
            count += arr[i].length + 1;
        }
    }
}

function checkValidName(name) {
    return -1
}

function handlerCommand(command, row) {
    if (regexCreateContact.test(command)) {
        const name = command.slice("Создай контакт ".length);
        // console.log("create: " + name);
        // checkSpacesAndNumbers(row, command, []);
        const index = checkValidName(name);
        if (index !== -1) {
            throw new syntaxError(row, index + 1);
        }
        if (!phoneBook.has(name)) {
            phoneBook.set(name, {
                phones: new Set(),
                emails: new Set()
            });
        }
    } else if (regexDeleteContact.test(command)) {
        const name = command.slice("Удали контакт ".length);
        // console.log("del: " + name);
        if (name === "") {
            return;
        }
        checkSpacesAndNumbers(row, command, [])
        const index = checkValidName(name);
        if (index !== -1) {
            throw new syntaxError(row, index + 1);
        }
        if (phoneBook.has(name)) {
            phoneBook.delete(name);
        }
    } else if (regexAddForContact.test(command)) {
        let parametersString = command.slice(command.indexOf("Добавь ") + "Добавь ".length, command.indexOf(" для контакта"));
        let name = command.slice(command.indexOf("для контакта ") + "для контакта ".length);
        // console.log("addParam: " + name + " " + parametersString);
        if (name === "") {
            return;
        }
        const parameters = parseParameters(parametersString);
        checkSpacesAndNumbers(row, command, parameters.phones);
        if (phoneBook.has(name)) {
            for (let key in parameters) {
                parameters[key].forEach(element => {
                    phoneBook.get(name)[key].add(element);
                });
            }
        }
    } else if (regexDelForContact.test(command)) {
        let parametersString = command.slice(command.indexOf("Удали ") + "Удали ".length, command.indexOf(" для контакта"));
        let name = command.slice(command.indexOf("для контакта ") + "для контакта ".length);
        if (name === "") {
            return;
        }
        // console.log("dellParam: " + name + " " + parametersString);
        const parameters = parseParameters(parametersString);
        checkSpacesAndNumbers(row, command, parameters.phones);
        if (phoneBook.has(name)) {
            for (let key in parameters) {
                parameters[key].forEach(element => {
                    phoneBook.get(name)[key].delete(element);
                });
            }
        }
    } else if (regexShowForParameters.test(command)) {
        // const regexShowForParameters = /^Покажи (.+) для контактов, где есть (.+)/;
        let parametersString = command.slice(command.indexOf("Покажи ") + "Покажи ".length, command.indexOf(" для контактов, где есть"));
        let subStr = command.slice(command.indexOf("для контактов, где есть ") + "для контактов, где есть ".length);
        // console.log("showParam: " + subStr + " " + parametersString);
        const order = parametersString.split(" и ");
        const parameters = parseParameters(parametersString);
        checkSpacesAndNumbers(row, command, parameters.phones);

        for (let key of phoneBook.entries()) {
            let flag = false;
            if (new RegExp(subStr).test(key[0])) {
                flag = true;
            } else {
                for (let keyParam in key[1]) {
                    for (let keyString of key[1][keyParam].values()) {
                        if (new RegExp(subStr).test(keyString)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
            }
            if (flag) {
                let ans = [];
                for (let i = 0; i < order.length; i++) {
                    if (order[i] === "имя") {
                        ans.push(key[0]);
                    } else if (order[i] === "телефоны") {
                        ans.push(getAllEntity(key[1]["phones"], true));
                    } else if (order[i] === "почты") {
                        ans.push(getAllEntity(key[1]["emails"]))
                    }
                }
                answer.push(ans.join(";"));
            }
        }

    } else if (regexDelForParameters.test(command)) {
        let subStr = command.slice(command.indexOf("где есть ") + "где есть ".length);
        checkSpacesAndNumbers(row, command, []);
        // console.log("DellParam: " + subStr);
        for (let key of phoneBook.entries()) {
            let flag = false;
            if (new RegExp(subStr).test(key[0])) {
                flag = true;
            } else {
                for (let keyParam in key[1]) {
                    for (let keyString of key[1][keyParam].values()) {
                        if (new RegExp(subStr).test(keyString)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
            }
            if (flag) {
                phoneBook.delete(key[0]);
            }
        }

    } else {
        let arr = command.split(" ");

        syntaxError(row, 1);
    }
}
}

function getAllEntity(setNum, isNum) {
    let ans = [];
    for (let key of setNum.values()) {
        if (isNum) {
            key = getNumber(key);
        }
        ans.push(key);
    }
    return ans.join(',');
}

function getNumber(str) {
    return "+7 (" + str.slice(0, 3) + ") " + str.slice(3, 6) + "-" + str.slice(6, 8) + "-" + str.slice(8);
}

function parseParameters(str) {
    const arr = str.split(" и ");
    let result = {
        phones: [],
        emails: []
    }
    for (let i = 0; i < arr.length; i++) {
        if (arr[i].startsWith("телефон ")) {
            result.phones.push(arr[i].slice("телефон ".length));
        } else if (arr[i].startsWith("почту ")) {
            result.emails.push(arr[i].slice("почту ".length));
        }
    }
    return result;
}



// const query = 'Создай контакт Григорий;' +
//     'Добавь телефон 1231231231 для контакта Григорий;' +
//     'Создай контакт Василий;' +
//     'Создай контакт Иннокентий;' +
//     'Покажи имя и имя и имя для контактов, где есть лий;'
// console.log(run(query));
// parseParameters("телефон 5556667788 и телефон 5556667787 и почту grisha@example.com")
module.exports = { phoneBook, run };
// const query = 'Создай контакт Григорий;прпрп;' +
//     'Создай контакт Василий;' +
//     'Создай контакт Иннокентий;' +
//     'Покажи имя и имя и имя для контактов, где есть ий;'