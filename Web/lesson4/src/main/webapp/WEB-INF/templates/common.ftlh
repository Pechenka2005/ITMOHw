<#-- @ftlvariable name="curPage" type="java.lang.String" -->
<#-- @ftlvariable name="logged_user_id" type="java.lang.String" -->

<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-vs-coronavirus-65.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user true
                />
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <#if curPage == "${item.href}">
                            <a href="${item.href}" class="underLine">${item.name}</a>
                        <#else>
                            <a href="${item.href}">${item.name}</a>
                        </#if>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <section>
            <div class="header">Information</div>
            <div class="body">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores enim expedita facilis
                fugiat ipsum iste nobis reprehenderit tempore ut voluptatibus?
            </div>
            <div class="footer">
                <a href="#">View all</a>
            </div>
        </section>
        <section>
            <div class="header">Information</div>
            <div class="body">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores enim expedita facilis
                fugiat ipsum iste nobis reprehenderit tempore ut voluptatibus?
            </div>
            <div class="footer">
                <a href="#">View all</a>
            </div>
        </section>
        <section>
            <div class="header">Information</div>
            <div class="body">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores enim expedita facilis
                fugiat ipsum iste nobis reprehenderit tempore ut voluptatibus?
            </div>
            <div class="footer">
                <a href="#">View all</a>
            </div>
        </section>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2020 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro userlink u nameOnly>
    <#if nameOnly == true>
        <#if logged_user_id == u.id>*</#if>
        <a href="/user?handle=${u.handle}">${u.name}</a>
    <#else>
        <a href="/user?handle=${u.handle}" class="${u.color}">${u.name}</a>
    </#if>

</#macro>


<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>



