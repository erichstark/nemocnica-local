<#-- @ftlvariable name="user" type="sk.stuba.fei.team.local.domain.Employee" -->
<#import "lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<@pt.menuFooterPage pageTitle="Timovy projekt">

<div>
    <h1>${user.prefix_title!} ${user.firstName!} ${user.lastName!} ${user.suffix_title!}</h1>
    <hr>

    <h3>Zoznam ambulancií</h3>
    <#if user.offices?? && user.offices?has_content>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th style="width: 50px;">#</th>
                <th style="text-align: left">Názov ambulancie</th>
                <th>Telefónne číslo</th>
                <th style="width: 60px; text-align: right">Zobraz</th>
            </tr>
            </thead>
            <tbody>
                <#list user.offices as office>
                <tr>
                    <td>${office_index + 1}</td>
                    <td style="text-align: left">${office.name}</td>
                    <td>${office.phone}</td>
                    <td style="text-align: center">
                        <a class="btn btn-info btn-sm " href="<@spring.url '/office/' + office.id />">
                            <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
                        </a>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
    <#else>
        <h4 class="text-muted">Nepriradené žiadne ambulancie.</h4>
    </#if>
</div>

</@pt.menuFooterPage>