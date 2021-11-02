'use strict';


const DANNY = "Danny";
const RUSTY = "Rusty";
const LINUS = "Linus";
const BANK = "Bank";
/**
 * @param {Object} schedule Расписание Банды
 * @param {number} duration Время на ограбление в минутах
 * @param {Object} workingHours Время работы банка
 * @param {string} workingHours.from Время открытия, например, "10:00+5"
 * @param {string} workingHours.to Время закрытия, например, "18:00+5"
 * @returns {Object}
 */
function getAppropriateMoment(schedule, duration, workingHours) {
    let robberyMoments = [];
    let curNames = {
        Danny: 0,
        Rusty: 0,
        Linus: 0,
        Bank: 0,
    }
    let endPoint = 0;
    let curRob = 0;
    const diffBank = +workingHours.from.substr(6);
    const HHFromBank = +workingHours.from.substr(0, 2);
    const MMFromBank = +workingHours.from.substr(3, 2);
    const HHToBank = +workingHours.to.substr(0, 2);
    const MMtoBank = +workingHours.to.substr(3, 2);

    let timeLines = [];

    function addMoments(moment, direction, player) {
        let currDiff = +moment.substr(9) - diffBank;
        let currDay = moment.substr(0, 2);
        let curHH = +moment.substr(3, 2) - currDiff;
        let curMM = +moment.substr(6, 2);
        if (currDay === "ВТ") {
            curHH += 24;
        } else if (currDay === "СР") {
            curHH += 48;
        }
        return {
            HH: curHH,
            MM: curMM,
            direction: direction,
            player: player
        }
    }

    for (let key in schedule) {
        let player = schedule[key];
        player.forEach(timeLine => {
            timeLines.push(
                addMoments(timeLine.from, "from", key),
                addMoments(timeLine.to, "to", key)
            );
        });
    }

    function addAnswer(a, b) {
        a = a.HH * 60 + a.MM;
        b = b.HH * 60 + b.MM;
        for (let i = a + duration; i <= b; i += 30) {
            robberyMoments.push(getDate(i - duration));
        }
    }

    function getDate(intDate) {
        let dd = Math.floor(intDate / 1440);
        intDate = intDate % 1440;
        let hh = Math.floor(intDate / 60);
        let mm = intDate % 60;
        let day = dd === 0 ? "ПН" : dd === 1 ? "ВТ" : "СР";
        return {
            day: day,
            hh: hh,
            mm: mm
        }
    }
    timeLines.push(
        addMoments("ПН " + workingHours.from, "from", "Bank"),
        addMoments("ПН " + workingHours.to, "to", "Bank"),
        addMoments("ВТ " + workingHours.from, "from", "Bank"),
        addMoments("ВТ " + workingHours.to, "to", "Bank"),
        addMoments("СР " + workingHours.from, "from", "Bank"),
        addMoments("СР " + workingHours.to, "to", "Bank"),
    )

    timeLines.sort(function(a, b) {
        if (a.HH === b.HH) {
            return a.MM - b.MM;
        }
        return a.HH - b.HH;
    });

    function isValid() {
        return (curNames[RUSTY] === 0 &&
            curNames[DANNY] === 0 &&
            curNames[LINUS] === 0 &&
            curNames[BANK] === 1);
    }

    let lastValid = false;
    let lastTimeLine = {};
    for (let i = 0; i < timeLines.length; i++) {
        let x = timeLines[i];
        if (x.player == BANK) {
            endPoint++;
        }
        if (x.direction == "from") {
            curNames[x.player]++;
        } else {
            curNames[x.player]--;
        }

        if (isValid()) {
            if (!lastValid) {
                lastValid = true;
                lastTimeLine = x;
            }
        } else {
            if (lastValid) {
                addAnswer(lastTimeLine, x);
                lastValid = false;
            }
        }


        if (endPoint === 6) {
            break;
        }
    }
    return {
        /**
         * Найдено ли время
         * @returns {boolean}
         */
        exists() {
            return robberyMoments.length !== 0;
        },

        /**
         * Возвращает отформатированную строку с часами
         * для ограбления во временной зоне банка
         *
         * @param {string} template
         * @returns {string}
         *
         * @example
         * ```js
         * getAppropriateMoment(...).format('Начинаем в %HH:%MM (%DD)') // => Начинаем в 14:59 (СР)
         * ```
         */
        format(template) {
            if (curRob > robberyMoments.length - 1) {
                return "";
            }
            let HH = robberyMoments[curRob].hh;
            if (Math.floor(HH / 10) === 0) {
                HH = "0" + HH;
            }
            let MM = robberyMoments[curRob].mm;
            if (Math.floor(MM / 10) === 0) {
                MM = "0" + MM;
            }
            template = template.replace("%DD", robberyMoments[curRob].day);
            template = template.replace("%HH", HH);
            template = template.replace("%MM", MM);
            return template;
        },

        /**
         * Попробовать найти часы для ограбления позже [*]
         * @note Не забудь при реализации выставить флаг `isExtraTaskSolved`
         * @returns {boolean}
         */
        tryLater() {
            if (curRob + 1 >= robberyMoments.length) {
                return false;
            }
            curRob++;
            return true;
        }
    };
}



module.exports = {
    getAppropriateMoment
};