<#-- @ftlvariable name="office" type="sk.stuba.fei.team.local.domain.Office" -->
<#-- @ftlvariable name="appointments" type="sk.stuba.fei.team.local.domain.Appointment[]" -->
<#import "../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<@pt.menuFooterPage pageTitle="Timovy projekt">
<div class="panel-body">
    <h1 class="text-center">${office.name}</h1>
    <hr>
    <h1 id="time" class="text-center"></h1>

    <h3 id="date" class="text-center"></h3>
</div>
<hr>
<div class="col-xs-6">
    <table id="ordered" class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Vyvolať</th>
            <th>Odobrať</th>
            <th>Objednávka</th>
            <th>Čas</th>
            <th>Detail</th>
        </tr>
        </thead>
        <tbody>

            <#list appointments?sort_by('intervalStart') as appointment>
            <tr>
                <td class="text-center">
                    <a href="#" style="color: black"
                       onclick="invoke(this, '${appointment.patient.firstName} ${appointment.patient.surname}')">
                        <span class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span>
                    </a>
                </td>
                <td class="text-center">
                    <a href="#" style="color: #c9302c" onclick="removeRow(this)">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                </td>
                <td>${appointment.patient.firstName} ${appointment.patient.surname}</td>
                <td><span>${appointment.intervalStart}</span></td>
                <td class="text-center">
                    <a target="_blank"
                       href="<@spring.url '/office/${office.id}/patientdetail/' + appointment.patient.username/>"
                       style="color: #5bc0de">
                        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                    </a>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>

<div class="col-xs-6" style="border-left: 1px solid #2c3e50">
    <table id="waitning" class="table table-striped table-hover">
        <thead>
        <tr>
            <th class="text-center">Poradové číslo</th>
            <th class="text-center">Čas pridania</th>
            <th class="text-center">Vyvolať</th>
            <th>Odobrať</th>
        </tr>
        </thead>
        <tbody>
            <#list 1..8 as i>
            <tr>
                <td>${i}</td>
                <td>8:00</td>
                <td class="text-center"><a href="#" style="color: black" onclick="invoke(this, '${i}')"><span
                        class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span></a></td>
                <td class="text-center"><a href="#" style="color: #c9302c" onclick="removeRow(this)"><span
                        class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
            </tr>
            </#list>
    </table>
</div>
<button class="btn btn-default pull-right" onclick="newOrderNumber()">
    <td class="text-center"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></td>
    Pridať poradové číslo
</button>
<input type="hidden" name="office_id" value="${office.id}">
<script src="<@spring.url '/js/office/call.js'/>"></script>
<script src="<@spring.url '/js/office/hours.js'/>"></script>
</@pt.menuFooterPage>


