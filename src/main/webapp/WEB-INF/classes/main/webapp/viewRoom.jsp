<html lang="en">
    <%@page import="java.util.*"%>
    <%@page import="uts.asd.model.*"%>
    <%@page import="uts.asd.model.dao.*"%>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Rooms</title>
    <jsp:include page="ConnServlet"/>
    <jsp:include page="nav.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 1px solid rgba(0, 0, 0, .125);
            border-radius: .25rem
        }

        .card>hr {
            margin-right: 0;
            margin-left: 0
        }

        .card>.list-group {
            border-top: inherit;
            border-bottom: inherit
        }

        .card>.list-group:first-child {
            border-top-width: 0;
            border-top-left-radius: calc(.25rem - 1px);
            border-top-right-radius: calc(.25rem - 1px)
        }

        .card>.list-group:last-child {
            border-bottom-width: 0;
            border-bottom-right-radius: calc(.25rem - 1px);
            border-bottom-left-radius: calc(.25rem - 1px)
        }

        .card>.card-header+.list-group,
        .card>.list-group+.card-footer {
            border-top: 0
        }

        .card-body {
            flex: 1 1 auto;
            padding: 1rem 1rem
        }

        .card-title {
            margin-bottom: .5rem
        }

        .card-subtitle {
            margin-top: -.25rem;
            margin-bottom: 0
        }

        .card-text:last-child {
            margin-bottom: 0
        }

        .card-link+.card-link {
            margin-left: 1rem
        }

        .card-header {
            padding: .5rem 1rem;
            margin-bottom: 0;
            background-color: rgba(0, 0, 0, .03);
            border-bottom: 1px solid rgba(0, 0, 0, .125)
        }

        .card-header:first-child {
            border-radius: calc(.25rem - 1px) calc(.25rem - 1px) 0 0
        }

        .card-footer {
            padding: .5rem 1rem;
            background-color: rgba(0, 0, 0, .03);
            border-top: 1px solid rgba(0, 0, 0, .125)
        }

        .card-footer:last-child {
            border-radius: 0 0 calc(.25rem - 1px) calc(.25rem - 1px)
        }

        .card-header-tabs {
            margin-right: -.5rem;
            margin-bottom: -.5rem;
            margin-left: -.5rem;
            border-bottom: 0
        }

        .card-header-pills {
            margin-right: -.5rem;
            margin-left: -.5rem
        }

        .card-img-overlay {
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            padding: 1rem;
            border-radius: calc(.25rem - 1px)
        }

        .card-img,
        .card-img-bottom,
        .card-img-top {
            width: 100%
        }

        .card-img,
        .card-img-top {
            border-top-left-radius: calc(.25rem - 1px);
            border-top-right-radius: calc(.25rem - 1px)
        }

        .card-img,
        .card-img-bottom {
            border-bottom-right-radius: calc(.25rem - 1px);
            border-bottom-left-radius: calc(.25rem - 1px)
        }

        .card-group>.card {
            margin-bottom: .75rem
        }

        @media (min-width:576px) {
            .card-group {
                display: flex;
                flex-flow: row wrap
            }

            .card-group>.card {
                flex: 1 0 0%;
                margin-bottom: 0
            }

            .card-group>.card+.card {
                margin-left: 0;
                border-left: 0
            }

            .card-group>.card:not(:last-child) {
                border-top-right-radius: 0;
                border-bottom-right-radius: 0
            }

            .card-group>.card:not(:last-child) .card-header,
            .card-group>.card:not(:last-child) .card-img-top {
                border-top-right-radius: 0
            }

            .card-group>.card:not(:last-child) .card-footer,
            .card-group>.card:not(:last-child) .card-img-bottom {
                border-bottom-right-radius: 0
            }

            .card-group>.card:not(:first-child) {
                border-top-left-radius: 0;
                border-bottom-left-radius: 0
            }

            .card-group>.card:not(:first-child) .card-header,
            .card-group>.card:not(:first-child) .card-img-top {
                border-top-left-radius: 0
            }

            .card-group>.card:not(:first-child) .card-footer,
            .card-group>.card:not(:first-child) .card-img-bottom {
                border-bottom-left-radius: 0
            }
        }

        .accordion-button {
            position: relative;
            display: flex;
            align-items: center;
            width: 100%;
            padding: 1rem 1.25rem;
            font-size: 1rem;
            color: #212529;
        }
    </style>
</head>

<body>
    <div class="row text-center container-fluid">
        <form method="POST" action="RoomFilterServlet">
            <section class="container"> 
                <div>
                    <h3>Beds</h3>
                    <input min="0" name="quantity" value="1" type="number">
                </div>
                <div>
                    <h3>Sort By</h3>
                    <select name="sort">
                        <option value="ascending">Ascending</option>
                        <option value="descending">Descending</option>
                    </select>
                    <p>
                </div>
                <div>
                    <button type="submit">Filter</button>
                </div>
            </section>
        </form>
        <p></p>
        <% for (RoomType roomType : (ArrayList<RoomType>)session.getAttribute("rooms")) { %>
        <div class="col-xl-3 col-md-4 col-sm-6 mb-4">
            <div class="card h-100 box-shadow">
                <h6 class="card-header text-muted"><%= roomType.getSuite()%></h6>
                <div class="card-body">
                    <p class="card-text">Price: $<%= roomType.getCost()%> per night</p>
                    <p class="card-text"><%= roomType.getNumBeds()%> Bed</p>
                    <p class="card-text"><%= roomType.getDescription()%></p>
                </div>
                <div class="card-footer">
                    <form method="get" action="ViewRoomServlet">
                        <input type="hidden" name="roomid" value="<%= roomType.getRoomTypeId()%>">
                        <button type="submit" class="btn btn-outline-info">More</a>
                    </form>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</body>
</html>